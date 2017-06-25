package com.pc.springmvc.service;

import com.pc.springmvc.dao.RemainderDao;
import com.pc.springmvc.model.Remainder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by pchaurishia
 */
@Service("remainderService")
@Transactional
public class RemainderServiceImpl implements RemainderService {
    @Autowired
    private RemainderDao remainderDao;

    
    public Remainder findById(Integer id) {
        Remainder remainder;
        try {
            remainder = remainderDao.findById(id);
        }catch (Exception e){
            //more exception handling can be done here
            remainder=null;
        }
        return remainder;
    }

    
    public Remainder findByName(String name) {
        Remainder remainder;
        try {
            remainder = remainderDao.findByName(name);
        }catch (Exception e){
            //more exception handling can be done here
            remainder=null;
        }
        return remainder;
    }

    
    public void saveRemainder(Remainder user) {
        remainderDao.saveRemainder(user);
    }

    
    public void updateRemainder(Remainder user) {
        remainderDao.updateRemainder(user);
    }

    
    public void deleteRemainderById(Integer id) {
        remainderDao.deleteRemainderById(id);
    }

    
    public List<Remainder> findAllRemainders() {
        return remainderDao.findAllRemainders();
    }

    
    public void deleteAllRemainders() {
        remainderDao.deleteAllRemainders();
    }

    
    public boolean isRemainderPresent(Remainder user) {
        return findByName(user.getName())!=null;
    }
}