package com.identitynumber.app.adapters;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.Filter;

import com.identitynumber.app.responseParsing.Country;

import java.util.ArrayList;

public class AutoCompleteAdapter extends ArrayAdapter {

    private ArrayList<Country> tempItems;
    private ArrayList<Country> suggestions;
    private ArrayList<String> tempItems2;
    private ArrayList<String> suggestions2;
    private FilterListeners filterListeners;
    private static final int TYPE_COUNTRY = 1;
    private static final int TYPE_STATE = 2;
    private int type;

    public AutoCompleteAdapter(Context context, ArrayList<String> items, int resource) {
        super(context, resource, 0, items);
        tempItems2 = new ArrayList<>(items);
        suggestions2 = new ArrayList<>();
        this.type = TYPE_STATE;
    }

    public AutoCompleteAdapter(Context context, int resource, ArrayList<Country> countries) {
        super(context, resource, 0, countries);
        tempItems = new ArrayList<>(countries);
        suggestions = new ArrayList<>();
        this.type = TYPE_COUNTRY;
    }

    public void setFilterListeners(FilterListeners filterFinishedListener) {
        filterListeners = filterFinishedListener;
    }

    @Override
    public Filter getFilter() {
        return nameFilter;
    }

    private Filter nameFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            if (type == TYPE_COUNTRY) {
                if (constraint != null) {
                    suggestions.clear();
                    for (Country names : tempItems) {
                        if (names.getTitle().toLowerCase().startsWith(constraint.toString().toLowerCase())) {
                            suggestions.add(names);
                        }
                    }
                    FilterResults filterResults = new FilterResults();
                    filterResults.values = suggestions;
                    filterResults.count = suggestions.size();
                    return filterResults;
                }
            } else if (type == TYPE_STATE) {
                if (constraint != null) {
                    suggestions2.clear();
                    for (String names : tempItems2) {
                        if (names.toLowerCase().startsWith(constraint.toString().toLowerCase())) {
                            suggestions2.add(names);
                        }
                    }
                    FilterResults filterResults = new FilterResults();
                    filterResults.values = suggestions2;
                    filterResults.count = suggestions2.size();
                    return filterResults;
                }
            }
            return new FilterResults();
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            if (type == TYPE_COUNTRY) {
                ArrayList<Country> filterList = (ArrayList<Country>) results.values;
                if (filterListeners != null && filterList != null)
                    filterListeners.filteringFinished(filterList, null, TYPE_COUNTRY);
                if (results != null && results.count > 0) {
                    clear();
                    for (Country item : filterList) {
                        add(item.getTitle());
                        notifyDataSetChanged();
                    }
                }
            } else if (type == TYPE_STATE) {
                ArrayList<String> filterList = (ArrayList<String>) results.values;
                if (filterListeners != null && filterList != null)
                    filterListeners.filteringFinished(null, filterList, TYPE_STATE);
                if (results != null && results.count > 0) {
                    clear();
                    for (String item : filterList) {
                        add(item);
                        notifyDataSetChanged();
                    }
                }
            }
        }
    };

    public interface FilterListeners {
        void filteringFinished(ArrayList<Country> filtersList, ArrayList<String> filterList2, int type);
    }
}