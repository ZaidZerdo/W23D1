package ba.bitcamp.zaid.recyclerproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    private static PersonList personList = new PersonList();
    private EditText nameText;
    private EditText surnameText;
    private Button addPersonButton;
    private RecyclerView recyclerView;
    private PersonAdapter personAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameText = (EditText) findViewById(R.id.editText);
        surnameText = (EditText) findViewById(R.id.editText2);
        addPersonButton = (Button) findViewById(R.id.button);

        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        personAdapter = new PersonAdapter(personList);
        recyclerView.setAdapter(personAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        addPersonButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Editable name = nameText.getText();
                Editable surname = surnameText.getText();

                personList.addPerson(name, surname);
                personAdapter.notifyDataSetChanged();
            }
        });
    }

    private class PersonView extends RecyclerView.ViewHolder {

        private TextView nameText;
        private TextView surnameText;
        private TextView dateText;

        public PersonView(View itemView) {
            super(itemView);

            nameText = (TextView) itemView.findViewById(R.id.textView2);
            surnameText = (TextView) itemView.findViewById(R.id.textView3);
            dateText = (TextView) itemView.findViewById(R.id.textView4);
        }
    }

    private class PersonAdapter extends RecyclerView.Adapter<PersonView> {

        private PersonList personList;

        public PersonAdapter(PersonList personList) {
            this.personList = personList;
        }

        @Override
        public PersonView onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(MainActivity.this);

            View view = layoutInflater.inflate(R.layout.person_layout, parent, false);

            return new PersonView(view);
        }

        @Override
        public void onBindViewHolder(PersonView holder, int position) {
            PersonModel person = personList.getPerson(position);

            holder.nameText.setText(person.getName());
            holder.surnameText.setText(person.getSurname());
            holder.dateText.setText(person.getDateAdded().toString());
        }

        @Override
        public int getItemCount() {
            return personList.getSize();
        }
    }
}
