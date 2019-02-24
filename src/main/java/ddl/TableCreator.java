package ddl;

import annotations.Column;
import annotations.Table;
import exception.DDLException;
import utitlity.Pair;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class TableCreator {
    /***
     *
     * @param classObject
     * @param <T>
     * @return Table Name provided inside Table Annotation above the param classObject declaration.
     */
    public<T> String getTableName(Class<T> classObject) {
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


    public<T> String getPK(Class<T> classObject) {
//        return Arrays.stream(classObject.getDeclaredFields())
//                .filter(primaryKeyPredicate)
//                .map(t-> Pair.of(t, t.getDeclaredAnnotation()))
//                .findFirst()
//                .orElseThrow(()->new DDLException("No Primary Key Found in " + ))
//                ;
        return null;

    }

    private <T>List<Pair<Field, List<Annotation>>> getPairOfFieldAndAnnotaions(Class<T> classObject) {
        return Arrays.stream(classObject.getDeclaredFields())
                .peek(t->t.setAccessible(true))
                .map(t->Pair.of(t, Arrays.stream(t.getDeclaredAnnotations()).collect(Collectors.toList())))
                .collect(Collectors.toList());
    }
    private Predicate<Field> primaryKeyPredicate = t-> {
        Column column = t.getDeclaredAnnotation(Column.class);
        return column!=null && column.isPK();
    };
}
