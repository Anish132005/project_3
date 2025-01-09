package in.co.rays.project_3.model;

import java.util.List;

import in.co.rays.project_3.dto.CartOverviewDto;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.exception.DuplicateRecordException;

public interface CartOverViewModelInt {
	
	public long add(CartOverviewDto dto) throws ApplicationException, DuplicateRecordException;

	public void delete(CartOverviewDto dto) throws ApplicationException;

	public void update(CartOverviewDto dto) throws ApplicationException, DuplicateRecordException;

	public CartOverviewDto findByPK(long pk) throws ApplicationException;

	public CartOverviewDto findByLogin(String login) throws ApplicationException;

	public List list() throws ApplicationException;

	public List list(int pageNo, int pageSize) throws ApplicationException;

	public List search(CartOverviewDto dto, int pageNo, int pageSize) throws ApplicationException;

	public List search(CartOverviewDto dto) throws ApplicationException;

}

