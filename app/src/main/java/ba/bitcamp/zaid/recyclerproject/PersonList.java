package ba.bitcamp.zaid.recyclerproject;

import android.text.Editable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

public class PersonList {
    private List<PersonModel> personList;

    public PersonList() {
        personList = new ArrayList<>();
    }

    public void addPerson(Editable name, Editable surname) {
        personList.add(new PersonModel(name.toString(), surname.toString()));
    }

    public PersonModel getPerson(int index) {
        return personList.get(index);
    }

    public int getSize() {
        return personList.size();
    }

    public void removePerson(UUID id) {
        Iterator<PersonModel> it = personList.iterator();
        while (it.hasNext()) {
            if (it.next().getID().equals(id)) {
                it.remove();
                return;
            }
        }
    }
}
