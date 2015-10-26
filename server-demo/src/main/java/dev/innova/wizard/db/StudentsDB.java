package dev.innova.wizard.db;

import dev.innova.wizard.beans.Student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentsDB {
    private static Map<Integer, Student> persons = new HashMap<Integer, Student>();

    static {
        persons.put(1, new Student(1, "Sajith", "Vijesekara", "sajith.vijesekara@gmail.com"));
        persons.put(2, new Student(2, "Hsenid", "hsenid", "hsenidmobile@gmail.com"));
        persons.put(3, new Student(3, "user1", "lsatName", "lsatName@gmail.com"));
        persons.put(4, new Student(4, "user2", "lsatName", "lsatName@gmail.com"));
    }

    public static Student getById(int id) {
        return persons.get(id);
    }

    public static List<Student> getAll() {
        List<Student> result = new ArrayList<Student>();
        for (Integer key : persons.keySet()) {
            result.add(persons.get(key));
        }
        return result;
    }

    public static int getCount() {
        return persons.size();
    }

    public static void remove() {
        if (!persons.keySet().isEmpty()) {
            persons.remove(persons.keySet().toArray()[0]);
        }
    }

    public static String save(Student student) {
        String result = "";
        if (persons.get(student.getId()) != null) {
            result = "Updated Student with id=" + student.getId();
        } else {
            result = "Added Student with id=" + student.getId();
        }
        persons.put(student.getId(), student);
        return result;
    }
}
