package com.pc.springmvc.dao;



import com.pc.springmvc.model.Remainder;

import java.util.List;

public interface RemainderDao {

   Remainder findByName(String name);

   Remainder findById(Integer id);

   void saveRemainder(Remainder user);

   void updateRemainder(Remainder user);

   void deleteRemainderById(Integer id);


   List<Remainder> findAllRemainders();

   void deleteAllRemainders();

   boolean isRemainderPresent(Remainder user);
}
