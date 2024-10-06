package com.caretrack.business.repos.interfaces;
import com.caretrack.business.repos.entities.SpecialtyEntity;
import com.caretrack.business.repos.entities.SpecialtyPatientCount;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

@Repository
public interface SpecialtyRepository extends JpaRepository<SpecialtyEntity, Long> {

    @Query("SELECT new com.caretrack.business.repos.entities.SpecialtyPatientCount(s.name, COUNT(DISTINCT a.patientId)) " +
            "FROM AppointmentEntity  a " +
            "JOIN DoctorEntity d ON a.doctorId = d.id " +
            "JOIN SpecialtyEntity s ON d.specialtyId = s.id " +
            "GROUP BY s.name " +
            "HAVING COUNT(DISTINCT a.patientId) >= :minPatients")
    List<SpecialtyPatientCount> getSpecialtiesWithAtLeastUniquePatients(@Param("minPatients") Long minPatients);
}
