package in.co.rays.project_3.model;

import java.util.List;

import in.co.rays.project_3.dto.ClientDTO;
import in.co.rays.project_3.exception.DuplicateRecordException;
import in.co.rays.project_3.exception.ApplicationException;

/**
 * Interface of Course model
 * @author Anish Malviya 
 *
 */
public interface ClientModelInt {
public long add(ClientDTO dto)throws ApplicationException,DuplicateRecordException;
public void delete(ClientDTO dto)throws ApplicationException;
public void update(ClientDTO dto)throws ApplicationException,DuplicateRecordException;
public List list()throws ApplicationException;
public List list(int pageNo,int pageSize)throws ApplicationException;
public List search(ClientDTO dto)throws ApplicationException;
public List search(ClientDTO dto,int pageNo,int pageSize)throws ApplicationException;
public ClientDTO findByPK(long pk)throws ApplicationException;
public ClientDTO findByName(String name)throws ApplicationException;
}
