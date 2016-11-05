//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package jack.me.sonweather.sp.framework;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Set;

public abstract class SharedPreferencesCompat {
    private static final Method sApplyMethod = findMethod(Editor.class, "apply", new Class[0]);
    private static final Method sGetStringSetMethod = findMethod(SharedPreferences.class, "getStringSet", new Class[]{String.class, Set.class});
    private static final Method sPutStringSetMethod = findMethod(Editor.class, "putStringSet", new Class[]{String.class, Set.class});

    private SharedPreferencesCompat() {
    }

    public static void apply(Editor editor) {
        try {
            invoke(sApplyMethod, editor, new Object[0]);
        } catch (NoSuchMethodException var2) {
            editor.commit();
        }
    }

    public static Set<String> getStringSet(SharedPreferences preferences, String key, Set<String> defValues) {
        try {
            return (Set)invoke(sGetStringSetMethod, preferences, new Object[]{key, defValues});
        } catch (NoSuchMethodException var5) {
            String serializedSet = preferences.getString(key, (String)null);
            return SetXmlSerializer.deserialize(serializedSet);
        }
    }

    public static void putStringSet(Editor editor, String key, Set<String> values) {
        try {
            invoke(sPutStringSetMethod, editor, new Object[]{key, values});
        } catch (NoSuchMethodException var4) {
            editor.putString(key, SetXmlSerializer.serialize(values));
        }

    }

    private static Method findMethod(Class<?> clazz, String name, Class... parameterTypes) {
        try {
            return clazz.getMethod(name, parameterTypes);
        } catch (NoSuchMethodException var4) {
            return null;
        }
    }

    public static <T> T invoke(Method method, Object obj, Object... args) throws NoSuchMethodException {
        if(method == null) {
            throw new NoSuchMethodException();
        } else {
            try {
                return (T) method.invoke(obj, args);
            } catch (IllegalAccessException var4) {
                ;
            } catch (IllegalArgumentException var5) {
                ;
            } catch (InvocationTargetException var6) {
                ;
            }

            throw new NoSuchMethodException(method.getName());
        }
    }
}
