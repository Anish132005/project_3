package in.co.rays.project_3.model;

import java.util.List;

import in.co.rays.project_3.dto.CustomerDto;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.exception.DuplicateRecordException;

public interface CustomerModelInt {
	
	public long add(CustomerDto dto) throws ApplicationException, DuplicateRecordException;

	public void delete(CustomerDto dto) throws ApplicationException;

	public void update(CustomerDto dto) throws ApplicationException, DuplicateRecordException;

	public CustomerDto findByPK(long pk) throws ApplicationException;

	public CustomerDto findByLogin(String login) throws ApplicationException;

	public List list() throws ApplicationException;

	public List list(int pageNo, int pageSize) throws ApplicationException;

	public List search(CustomerDto dto, int pageNo, int pageSize) throws ApplicationException;

	public List search(CustomerDto dto) throws ApplicationException;


}
