package dcd_eng.Patches;

import java.lang.Class;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

public class Invoker {
   private static ConcurrentHashMap<Class, HashMap<String, Field>> fields = new ConcurrentHashMap();
   private static ConcurrentHashMap<Class, HashMap<String, HashMap<Class[], Method>>> methods = new ConcurrentHashMap();
   public static final Logger LOGGER = Logger.getLogger(Invoker.class.toString());
   private static final HashMap<Class, Class> basics = new HashMap();
   private static final String NOT_MATCH = "NOT_MATCH";
   private static List<Class[]> allSuperClass;
   private static Object[] theArgs;

   public static <T> T getField(Object target, String name) {
      try {
         return (T)getField0(target, name).get(target);
      } catch (RuntimeException | IllegalAccessException var3) {
         return null;
      }
   }

   private static Field getField0(Object target, String name) {
      Class clazz = getClass(target);
      Field field = getFieldInCache(clazz, name);
      if (field != null) {
         return field;
      } else {
         for(Class c = clazz; c != Object.class && field == null; c = c.getSuperclass()) {
            try {
               field = c.getDeclaredField(name);
               setAccessible(field);
               save(clazz, name, field);
               return field;
            } catch (NoSuchFieldException var5) {}
         }

         LOGGER.severe("No field '" + name + "' found in target class '" + clazz.getName() + "' and super classes.");
         return null;
      }
   }

   public static void setField(Object target, String name, Object val) {
      Field field = getField0(target, name);

      try {
         if (field != null) {
            field.set(target, val);
         }
      } catch (RuntimeException | IllegalAccessException e) {
         ((Exception)e).printStackTrace();
      }

   }

   private static Method getMethod(Object target, String name, Class... paramTypes) {
      Class clazz = getClass(target);
      Method method = getMethodInCache(clazz, name, paramTypes);
      if (method != null) {
         return method;
      } else {
         for(Class c = clazz; c != Object.class && method == null; c = c.getSuperclass()) {
            try {
               method = c.getDeclaredMethod(name, paramTypes);
               setAccessible(method);
               save(clazz, name, method, paramTypes);
            } catch (RuntimeException | NoSuchMethodException var7) {
            }
         }

         if (method == null) {
            method = getSameNameMethodInCache(clazz, name, paramTypes);
            return method;
         } else {
            return method;
         }
      }
   }

   public static <T> T invoke(Object target, String name, Object... args) {
      try {
         Class[] params = getParamTypes(0L, args);
         Method method = getMethod(target, name, params);
         if (method != null) {
            return (T)method.invoke(target, args);
         }

         long typesCount = typesCount(args);

         for(long i = typesCount - 1L; i > 0L; --i) {
            try {
               method = getMethod(target, name, getParamTypes(i, args));
               if (method != null) {
                  save(getClass(target), name, method, params);
                  return (T)method.invoke(target, args);
               }
            } catch (Exception var11) {
            }
         }

         if (method == null) {
            List<Class[]> types = getSuperParamTypes(args);
            allSuperClass.clear();

            for(Class[] type : types) {
               try {
                  method = getMethod(target, name, type);
                  if (method != null) {
                     save(getClass(target), name, method, params);
                     return (T)method.invoke(target, args);
                  }
               } catch (Exception var10) {
               }
            }
         }
      } catch (InvocationTargetException | IllegalAccessException e) {
         ((ReflectiveOperationException)e).printStackTrace();
      }

      return null;
   }

   public static <T> T invoke(Method method, Object target, Object... args) {
      try {
         return (T)method.invoke(target, args);
      } catch (InvocationTargetException | RuntimeException | IllegalAccessException e) {
         ((Exception)e).printStackTrace();
         return null;
      }
   }

   private static void save(Class clazz, String name, Field field) {
      if (fields.containsKey(clazz) && fields.get(clazz) != null) {
         HashMap<String, Field> map = (HashMap)fields.get(clazz);
         if (map.get(name) == null) {
            map.put(name, field);
         }
      } else {
         HashMap<String, Field> map = new HashMap();
         map.put(name, field);
         fields.put(clazz, map);
      }

   }

   private static void save(Class clazz, String name, Method method, Class... paramTypes) {
      if (methods.get(clazz) == null) {
         HashMap<String, HashMap<Class[], Method>> map = new HashMap();
         HashMap<Class[], Method> map2 = new HashMap();
         map.put(name, map2);
         map2.put(paramTypes, method);
         methods.put(clazz, map);
      } else {
         HashMap<String, HashMap<Class[], Method>> map = (HashMap)methods.get(clazz);
         if (map.get(name) == null) {
            HashMap<Class[], Method> map2 = new HashMap();
            map.put(name, map2);
            map2.put(paramTypes, method);
         }
      }

   }

   private static Field getFieldInCache(Class clazz, String name) {
      return fields.get(clazz) == null ? null : (Field)((HashMap)fields.get(clazz)).get(name);
   }

   private static Method getMethodInCache(Class clazz, String name, Class... paramTypes) {
      return methods.get(clazz) != null && ((HashMap)methods.get(clazz)).get(name) != null ? (Method)((HashMap)((HashMap)methods.get(clazz)).get(name)).get(paramTypes) : null;
   }

   private static Method getSameNameMethodInCache(Class clazz, String name, Class... paramTypes) {
      if (methods.get(clazz) != null && ((HashMap)methods.get(clazz)).get(name) != null) {
         HashMap<Class[], Method> map = (HashMap)((HashMap)methods.get(clazz)).get(name);

         for(Class[] classes : map.keySet()) {
            if (isImplClassArray(classes, paramTypes)) {
               return (Method)map.get(classes);
            }
         }

         return null;
      } else {
         return null;
      }
   }

   private static boolean isImplClassArray(Class[] c1, Class[] c2) {
      if (c1.length == c2.length) {
         for(int i = 0; i < c1.length; ++i) {
            if (!c2[i].isAssignableFrom(c1[i])) {
               return false;
            }
         }
      }

      return true;
   }

   private static Class getClass(Object target) {
      return target instanceof Class ? (Class)target : target.getClass();
   }

   private static void setAccessible(Field field) {
      if (!Modifier.isPublic(field.getModifiers())) {
         field.setAccessible(true);
      }

      if (Modifier.isFinal(field.getModifiers())) {
         Field modifiersField = null;

         try {
            modifiersField = Field.class.getDeclaredField("modifiers");
            modifiersField.setAccessible(true);
            modifiersField.setInt(field, field.getModifiers() & -17);
         } catch (IllegalAccessException | NoSuchFieldException var3) {
         }
      }

   }

   private static void setAccessible(Method method) {
      if (!Modifier.isPublic(method.getModifiers())) {
         method.setAccessible(true);
      }

   }

   private static long typesCount(Object... args) {
      int cnt = 0;

      for(Object o : args) {
         if (basics.containsKey(o.getClass())) {
            ++cnt;
         }
      }

      return (long)(1 << cnt);
   }

   private static Class[] getParamTypes(long seed, Object... args) {
      Class[] params = new Class[args.length];

      for(int i = 0; i < args.length; ++i) {
         params[i] = args[i].getClass();
         if (basics.containsKey(params[i])) {
            if ((seed & 1L) == 0L) {
               params[i] = (Class)basics.get(params[i]);
            }

            seed >>>= 1;
         }
      }

      return params;
   }

   private static List<Class[]> getSuperParamTypes(Object... args) {
      allSuperClass.clear();
      theArgs = args;
      getSuperParamTypes(new Class[args.length], 0);
      return allSuperClass;
   }

   private static void getSuperParamTypes(Class[] params, int i) {
      if (i == theArgs.length) {
         allSuperClass.add(params);
      } else {
         params[i] = theArgs[i].getClass();
         if (basics.containsKey(params[i])) {
            getSuperParamTypes(params, i + 1);
            Class[] newArr = params.clone();
            newArr[i] = (Class)basics.get(params[i]);
            getSuperParamTypes(newArr, i + 1);
         } else {
            for(Class c : getSuperClasses(params[i])) {
               Class[] newArr = params.clone();
               newArr[i] = c;
               getSuperParamTypes(newArr, i + 1);
            }
         }

      }
   }

   private static List<Class> getSuperClasses(Class c) {
      Class[] interfaces = c.getInterfaces();
      List<Class> classes = new ArrayList();

      while(c != Object.class) {
         c = c.getSuperclass();
         classes.add(c);
      }

      for(Class cc : interfaces) {
         classes.add(cc);
      }

      return classes;
   }

   static {
      basics.put(Integer.class, Integer.TYPE);
      basics.put(Character.class, Character.TYPE);
      basics.put(Boolean.class, Boolean.TYPE);
      basics.put(Byte.class, Byte.TYPE);
      basics.put(Float.class, Float.TYPE);
      basics.put(Double.class, Double.TYPE);
      basics.put(Short.class, Short.TYPE);
      basics.put(Long.class, Long.TYPE);
      allSuperClass = new ArrayList();
      theArgs = null;
   }
}
