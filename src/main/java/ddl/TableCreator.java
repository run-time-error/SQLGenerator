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


    public<T> Field getPrimaryKeyField(Class<T> classObject) {
        List<Field> primaryKeyFields =  Arrays.stream(classObject.getDeclaredFields())
                .filter(primaryKeyPredicate)
                .collect(Collectors.toList());
        if(primaryKeyFields.isEmpty()){
            throw new DDLException("No Field found having Primary key property in "+classObject);
        }else if(primaryKeyFields.size()>1){
            throw new DDLException("Multiple Fields found having Primary key property in "+classObject);
        }
        return primaryKeyFields.get(0);
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
