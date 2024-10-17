package com.example.PrestaBanco.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.PrestaBanco.entities.BankExecutiveEntity;

@Repository
public interface BanExecutiveRepository extends JpaRepository<BankExecutiveEntity, Long> {

    public BankExecutiveEntity findByRut(String email);

    @Query("SELECT b FROM BankExecutiveEntity b WHERE b.phone = :phone")
    BankExecutiveEntity findByPhone(@Param("phone") String phone);

    @Query("SELECT b FROM BankExecutiveEntity b WHERE b.executive_id = :executive_id")
    BankExecutiveEntity findByExecutiveId(@Param("executive_id") Long executive_id);

    @Query("SELECT b FROM BankExecutiveEntity b WHERE LOWER(b.email) = LOWER(:email)")
    BankExecutiveEntity findByEmail(@Param("email") String email);

    @Query("SELECT b FROM BankExecutiveEntity b WHERE LOWER(b.name) = LOWER(:name)")
    BankExecutiveEntity findByName(@Param("name") String name);

    @Query("SELECT CASE WHEN COUNT(b) > 0 THEN TRUE ELSE FALSE END FROM BankExecutiveEntity b WHERE b.rut = :rut")
    boolean existsByRut(String rut);
}
