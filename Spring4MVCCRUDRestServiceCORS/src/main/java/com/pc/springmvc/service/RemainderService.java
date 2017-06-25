package com.pc.springmvc.service;


import com.pc.springmvc.model.Remainder;

import java.util.List;

/**
 * Created by pchaurishia 
 */
public interface RemainderService {
    Remainder findById(Integer id);

    Remainder findByName(String name);

    void saveRemainder(Remainder user);

    void updateRemainder(Remainder user);

    void deleteRemainderById(Integer id);

    List<Remainder> findAllRemainders();

    void deleteAllRemainders();

    public boolean isRemainderPresent(Remainder user);
}

