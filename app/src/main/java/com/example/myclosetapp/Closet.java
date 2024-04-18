package com.example.myclosetapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Closet {
    private DatabaseReference closet =
            FirebaseDatabase.getInstance().getReference();
    private static Closet instance = new Closet();
    private ArrayList<String> ids;
    private Closet(){
        ids = new ArrayList<>();
    }

    public static Closet getInstance(){
        if (instance == null){
            instance = new Closet();
        }
        return instance;
    }
    public boolean addClothes(@NonNull Clothing item){
        final boolean[] success = {false};

        closet.child(item.getIdentification()).setValue(item).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                ids.add(item.getIdentification());
                success[0] = true;
            }
        });
        /*closet.child(item.getType()).child(item.getIdentification())
        .setValue(item).addOnSuccessListener(new OnSuccessListener<Void>() {

            @Override
            public void onSuccess(Void unused) {
                ids.add(item.getIdentification());
                success[0] = true;
            }
        });*/
        return success[0];
    }

    public String createIDs(){
        boolean go = true;
        Random rand = new Random();
        String temp = Integer.toString(rand.nextInt());
        while (go){
            temp = Integer.toString(rand.nextInt());
            for (int i=0; i<ids.size(); i++){
                if(ids.get(i).equalsIgnoreCase(temp)){
                    go = true;
                }
            }
            go = false;
        }
        return temp;
    }

    /**
     * Removes given item from database, and removes ID from arraylist
     * @param item - item to be removed
     * @return - boolean value denoting success
     */
    public Boolean removeItem(Clothing item){
        Boolean[] success = {false};
        if (item!= null){
            String id = item.getIdentification();
            String type = item.getType();
            closet.child(id).removeValue(new DatabaseReference.CompletionListener() {
                @Override
                public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                    success[0] = true;
                    ids.remove(id);
                }
            });

        }

        return success[0];
    }

    public boolean clearCloset (){
        Boolean[] success = {false};
        closet.removeValue(new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                success[0] = true;
            }
        });

        return success[0];
    }

    public void addPictureID(String itemID,String type, String picID){
        closet.child(itemID).child("pictureID").setValue(picID);
    }

    /*public void searchCloset(String criteria){
        String orderby = "styles";
        closet.orderByChild(orderby).equalTo(criteria).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    for(DataSnapshot snap :snapshot.getChildren()){
                        Clothing match = snapshot.getValue()
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }*/

   /* public ArrayList<String> searchCloset (ArrayList<String> criteria){
        ArrayList<String> searchResults = new ArrayList<>();
        DatabaseReference stlyes = closet.child("styles");
        for(String x: ids){
            for(String y:criteria){
                stlyes.orderByChild(y).equalTo(y).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()){
                            searchResults.add(x);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        }

        return searchResults;
    }

    private ArrayList<String> uniqueResults (ArrayList<String> resultList,
                                     String match){
        for (String check:resultList){
            if (match.equalsIgnoreCase(check)){
               return resultList;
            }
        }
        resultList.add(match);
        return resultList;
    }*/
  /* public ArrayList<String> searchCloset (ArrayList<String> criteria) {
        ArrayList<String> resultIds = new ArrayList<>();
        if(criteria != null){
           // String [] meta = criteriaData(criteria);
           // int j = Integer.parseInt(meta[0]);
           Iterator<String> itr = criteria.iterator();
           while(itr.hasNext()){
               String match = itr.next();
               if()
           }

        }
        return resultIds;
    }*/

    /*private DatabaseReference pathFinder(){
        DatabaseReference ref = closet.

        return ref;
    }*/


    /**
     * Parses Criteria arraylist to find:
     *      - size(index 0)
     *      -
     * @return Array containing details of critera arraylist
     */
    /*private String[] criteriaData (ArrayList<String> criteria){
        String[] data = new String[1];
        data[0] = Integer.toString(criteria.size());
        return data;
    }*/
}
