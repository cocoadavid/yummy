package com.codecool.yummy;

import com.codecool.yummy.model.Comment;
import com.codecool.yummy.model.Picture;
import com.codecool.yummy.model.Recipe;
import com.codecool.yummy.model.User;
import com.sun.org.apache.regexp.internal.RE;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.swing.text.html.parser.Entity;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by szilarddavid on 2017.06.21..
 */
public class Yummy {

    public static void populateDb(EntityManager em){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date1= Calendar.getInstance().getTime();
        Date date2= Calendar.getInstance().getTime();
        try {
            date1=sdf.parse("2000-01-01");
            date1=sdf.parse("2000-01-01");

        }catch (ParseException e){
            e.printStackTrace();
        }
        User user1 =new User();
        User user2 =new User();
        Recipe recipe1=new Recipe();
        Recipe recipe2=new Recipe();
        Picture picture1=new Picture("pic1","asd");
        Picture picture2=new Picture("pic2","asd2");
        Comment com1=new Comment(user1,date1,"vmi komment");
        Comment com2=new Comment(user2,date2,"vmi komment2");

        // TESTING ADD METHODS
        user1.addFollowing(user2);
        user2.addFollowing(user1);
        user1.addRecipe(recipe1);
        user2.addRecipe(recipe2);
        recipe1.addPicture(picture1);
        recipe2.addPicture(picture2);
        recipe1.addComment(com1);
        recipe1.addComment(com2);

        // TESTING SETTERS
        user1.setName("János");
        user2.setName("Eduardo");
        user1.setPassword("pass1");
        user2.setPassword("pass2");
        recipe1.setCategory("dessert");
        recipe2.setCategory("soup");
        recipe1.setName("lasagne");
        recipe2.setName("Ragacsos malaccsászár koreai BBQ-val");

        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(user1);
        em.persist(user2);
        em.persist(recipe1);
        em.persist(recipe2);
        em.persist(picture1);
        em.persist(picture2);
        em.persist(com1);
        em.persist(com2);
        transaction.commit();


    }

    public static void main(String[] args){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("yummy");
        EntityManager em =emf.createEntityManager();
        populateDb(em);
        em.close();
        emf.close();
    }

}
