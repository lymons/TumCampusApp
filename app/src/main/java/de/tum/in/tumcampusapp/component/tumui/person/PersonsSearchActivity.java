package de.tum.in.tumcampusapp.component.tumui.person;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import de.tum.in.tumcampusapp.R;
import de.tum.in.tumcampusapp.api.tumonline.TUMOnlineConst;
import de.tum.in.tumcampusapp.component.other.general.RecentsDao;
import de.tum.in.tumcampusapp.component.other.general.model.Recent;
import de.tum.in.tumcampusapp.component.other.generic.activity.ActivityForSearchingTumOnline;
import de.tum.in.tumcampusapp.component.other.generic.adapter.NoResultsAdapter;
import de.tum.in.tumcampusapp.component.tumui.person.model.Person;
import de.tum.in.tumcampusapp.component.tumui.person.model.PersonList;
import de.tum.in.tumcampusapp.database.TcaDb;

/**
 * Activity to search for employees.
 */
public class PersonsSearchActivity extends ActivityForSearchingTumOnline<PersonList> implements OnItemClickListener {
    private static final String P_SUCHE = "pSuche";

    /**
     * List to display the results
     */
    private ListView lvPersons;
    private RecentsDao recentsDao;

    public PersonsSearchActivity() {
        super(TUMOnlineConst.Companion.getPERSON_SEARCH(), R.layout.activity_persons, PersonSearchSuggestionProvider.AUTHORITY, 3);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lvPersons = findViewById(R.id.lstPersons);
        lvPersons.setOnItemClickListener(this);

        recentsDao = TcaDb.getInstance(this)
                          .recentsDao();

        ArrayAdapter<Person> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, android.R.id.text1, getRecents());

        if (adapter.getCount() == 0) {
            openSearch();
        } else {
            lvPersons.setAdapter(adapter);
            lvPersons.setOnItemClickListener(this);
            lvPersons.requestFocus();
        }
    }

    private ArrayList<Person> getRecents() {
        List<Recent> recentList = recentsDao.getAll(RecentsDao.PERSONS);
        ArrayList<Person> personList = new ArrayList<>(recentList.size());
        for (Recent r : recentList) {
            personList.add(Person.Companion.fromRecent(r));
        }
        return personList;
    }

    @Override
    public void onItemClick(AdapterView<?> a, View v, int position, long id) {
        Person person = (Person) lvPersons.getItemAtPosition(position);

        // store selected person ID in bundle to get in in StaffDetails
        Bundle bundle = new Bundle();
        bundle.putSerializable("personObject", person);

        // show detailed information in new activity
        Intent intent = new Intent(this, PersonsDetailsActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);

        String lastSearch = person.getId() + "$" + person.toString()
                                                         .trim();
        recentsDao.insert(new Recent(lastSearch, RecentsDao.PERSONS));
    }

    @Override
    protected void onStartSearch() {
        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, android.R.id.text1, getRecents());
        lvPersons.setAdapter(adapter);
    }

    @Override
    public void onStartSearch(String query) {
        requestHandler.setParameter(P_SUCHE, query);
        requestFetch();
    }

    private void proceedToPersonDetails(PersonList response) {
        lvPersons.setAdapter(null);
        Bundle bundle = new Bundle();
        bundle.putSerializable("personObject", response.getPersons()
                                                       .get(0));
        Intent intent = new Intent(this, PersonsDetailsActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    /**
     * Handles the XML response from TUMOnline by de-serializing the information
     * to model entities.
     *
     * @param response The de-serialized data from TUMOnline.
     */
    @Override
    public void onLoadFinished(PersonList response) {
        if (response.getPersons() == null || response.getPersons()
                                                     .isEmpty()) {
            lvPersons.setAdapter(new NoResultsAdapter(this));
        } else if (response.getPersons()
                           .size() == 1) {
            proceedToPersonDetails(response);
        } else {
            ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, android.R.id.text1, response.getPersons());
            lvPersons.setAdapter(adapter);
        }
    }
}
