package com.tuyano.springboot.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tuyano.springboot.MyData;

@Repository
public interface MyDataRepository extends JpaRepository<MyData, Long> {
	public Optional<MyData> findById(Long name);
	//public MyData findById(Long name);
	//public Iterable<MyData> findAllOrderByIdAsc();
	//public List<MyData> findAllByOrderByIdAsc();

	public List<MyData> findByNameLike(String name);

	public List<MyData> findByIdIsNotNullOrderByIdDesc();

	public List<MyData> findByIdIsNotNullOrderByIdAsc();

	public List<MyData> findByAgeGreaterThan(Integer age);

	public List<MyData> findByAgeBetween(Integer age1, Integer age2);

	@Query("SELECT d FROM MyData d ORDER BY d.name")
	List<MyData> findAllOrderByName();

	@Query("from MyData where age > :min and age < :max")
	public List<MyData> findByAge2(@Param("min") int min, @Param("max") int max);
}
