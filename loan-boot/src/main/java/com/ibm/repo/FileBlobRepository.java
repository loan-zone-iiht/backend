package com.ibm.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ibm.entity.FileBlob;

/**
 * Class {FileBlobRepository} is a repository interface
 * for FileBlob entity.
 * 
 * 
 * @author Saswata Dutta
 */

public interface FileBlobRepository extends JpaRepository<FileBlob, Integer>{
	List<FileBlob> findAllByCustomerId(int custId);
}
