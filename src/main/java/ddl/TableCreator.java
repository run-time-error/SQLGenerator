package ddl;

import annotations.Column;
import annotations.Table;
import exception.DDLException;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

class TableCreator {
    /***
     * @param <T> The Parameter type
     * @param classObject The Class Object to get the table name info from.
     * @return Table Name provided inside Table Annotation above the param classObject declaration.
     */
    <T> String getTableName(Class<T> classObject) {
        Table table = classObject.getDeclaredAnnotation(Table.class);
        String tableName;
        if(table == null) {
            throw new DDLException("No Table Name Annotation Found for " + classObject);
        }else {
            tableName = table.value().trim();
            if(tableName.length() == 0) {
                throw new DDLException("Table Name Not Found inside Table Annotation for " + classObject);
            }
        }

        return tableName;
    }


    <T> Map<Field, Column> getAllColumns(Class<T> classObject) {
        return Arrays.stream(classObject.getDeclaredFields())
                .filter(t->t.getDeclaredAnnotation(Column.class) != null)
                .collect(Collectors.toMap(t->t, t->t.getDeclaredAnnotation(Column.class)));

    }
}
