package org.service.mailing.repository;

import com.tryadhawk.airtable.*;
import com.tryadhawk.airtable.v0.Record;

import javax.ejb.Stateful;
import javax.ejb.Stateless;
import java.util.*;
@Stateless
public class AirtableRepository {

    Airtable airtable;
    SyncTable<HashMap> table;
    public AirtableRepository() {
        Airtable airtable = Airtable.builder()
                .config(Configuration.builder().apiKey("keyDv8GhbNvXfKdg5").build())
                .build();
        table = airtable.buildSyncTable("appuFRh8k8mBSS29c", "table", HashMap.class);
    }
    public Map<String,String> getData(){

        Map l = new HashMap<String,String>();
        List<Record<HashMap>> resMap = table.select(Query.builder().fields(Arrays.asList(new String[]{"email", "firstName"})).build());
        for (Record<HashMap> el:resMap) {
            l.put((String) el.getFields().get("firstName"),(String) el.getFields().get("email"));
        }
        return l;
    }

}

