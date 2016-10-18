package sk.juko.common;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by book on 10/18/16.
 */
public class ClazzUtils {
    /**
     * Creates new object of specific clazz
     * @param clazz class type
     * @param constructorValues num of values has to mach with constructor size
     * @param <T> any type
     * @return created class of clazz
     * @throws Throwable exception in cause of some problems
     */
    public static <T> Object newClass(Class<T> clazz, Object... constructorValues) throws Throwable {
        List<Field> fields = getFields(clazz);
        Class[] variables = new Class[fields.size()];
        for(int i = 0; i < variables.length; i++){
            variables[i] = fields.get(i).getType();
        }
        return Class.forName(clazz.getName()).getConstructor(variables).newInstance(constructorValues);
    }

    /**
     * Retrieve all variable names in the clazz
     * @param clazz object class
     * @param <T> type
     * @return variable names in upper causes array
     */
    public static <T> String[] getVariables(final Class<T> clazz){
        List<Field> fields = getFields(clazz);
        String[] columnNames = new String[fields.size()];
        for(int i = 0; i < columnNames.length; i++){
            columnNames[i] = fields.get(i).getName().toUpperCase();
        }
        return columnNames;
    }

    /**
     * Retrieve all values of the object as an array
     * @param obj object to retrieve values
     * @return values as an array
     * @throws IllegalAccessException exception in cause of problems
     */
    public static String[] getValues(final Object obj) throws IllegalAccessException {
        String[] variables = getVariables(obj.getClass());
        String[] values = new String[variables.length];
        List<Field> fields = getFields(obj.getClass());
        int counter = 0;
        for(Field field : fields){
            field.setAccessible(true);
            values[counter++] = field.get(obj).toString();
            field.setAccessible(false);
        }
        return values;
    }

    /**
     * Retrieves all fields from class till all super classes
     * @param clazz Class
     * @param <T> type
     * @return all fields of inherited superclasses and class it self
     */
    private static <T> List<Field> getFields(Class<T> clazz) {
        List<Field> fields = new ArrayList<>();
        Class cls = clazz;
        while (null != cls){
            fields.addAll(Arrays.asList(cls.getDeclaredFields()));
            cls = cls.getSuperclass();
        }
        return fields;
    }
}
