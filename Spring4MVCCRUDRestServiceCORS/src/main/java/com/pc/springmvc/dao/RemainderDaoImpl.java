package com.pc.springmvc.dao;

import com.pc.springmvc.model.Remainder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class RemainderDaoImpl implements RemainderDao {

   NamedParameterJdbcTemplate namedParameterJdbcTemplate;
   private static final AtomicLong counter = new AtomicLong();

   @Autowired
   public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
      this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
   }

   
   public Remainder findByName(String name) {

      Map<String, Object> params = new HashMap<String, Object>();
      params.put("name", name);

      String sql = "SELECT * FROM remainders WHERE name=:name";

      Remainder result = namedParameterJdbcTemplate.queryForObject(
            sql,
            params,
            new RemainderMapper());


      return result;

   }


   private static final class RemainderMapper implements RowMapper<Remainder> {

      public Remainder mapRow(ResultSet rs, int rowNum) throws SQLException {
         Remainder user = new Remainder();
         user.setId(rs.getInt("id"));
         user.setName(rs.getString("name"));
         user.setDescription(rs.getString("description"));
         user.setDueDate(rs.getDate("dueDate"));
         user.setStatus(rs.getString("status"));
         return user;
      }
   }

   
   public Remainder findById(Integer id) {
      Map<String, Object> params = new HashMap<String, Object>();
      params.put("id", id);

      String sql = "SELECT * FROM remainders WHERE id=:id";

      Remainder result = namedParameterJdbcTemplate.queryForObject(
            sql,
            params,
            new RemainderMapper());


      return result;
   }


   
   public void saveRemainder(Remainder remainder) {
      String SQL = "INSERT INTO remainders (id,name,description,dueDate,status) VALUES (:id,:name,:description,:dueDate,:status)";
      Map namedParameters = new HashMap();
      namedParameters.put("id", counter.incrementAndGet());
      namedParameters.put("name",remainder.getName() );
      namedParameters.put("description", remainder.getDescription());
      namedParameters.put("dueDate", remainder.getDueDate());
      namedParameters.put("status", remainder.getStatus());
      namedParameterJdbcTemplate.update(SQL, namedParameters);
   }

   
   public void updateRemainder(Remainder remainder) {
      String SQL = "UPDATE remainders SET name = :name, " +
            "description = :description, dueDate = :dueDate," +
            "status = :status" +
            " WHERE id = :id";
      Map namedParameters = new HashMap();
      namedParameters.put("id", remainder.getId());
      namedParameters.put("name",remainder.getName() );
      namedParameters.put("description", remainder.getDescription());
      namedParameters.put("dueDate", remainder.getDueDate());
      namedParameters.put("status", remainder.getStatus());
      namedParameterJdbcTemplate.update(SQL, namedParameters);
   }

   
   public void deleteRemainderById(Integer id) {
      String SQL = "DELETE FROM remainders WHERE id = :id";
      SqlParameterSource namedParameters = new MapSqlParameterSource("id",id);
      namedParameterJdbcTemplate.update(SQL, namedParameters);
   }

   
   public List<Remainder> findAllRemainders() {
      Map<String, Object> params = new HashMap<String, Object>();

      String sql = "SELECT * FROM remainders";

      List<Remainder> result = namedParameterJdbcTemplate.query(sql, params, new RemainderMapper());

      return result;
   }

   
   public void deleteAllRemainders() {
      String SQL = "DELETE FROM remainders";
      namedParameterJdbcTemplate.getJdbcOperations().update(SQL);
   }

   
   public boolean isRemainderPresent(Remainder remainder) {
      return findByName(remainder.getName())!=null;
   }
}