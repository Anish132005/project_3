package in.co.rays.project_3.model;

import java.util.List;

import in.co.rays.project_3.dto.FavoriteListDto;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.exception.DuplicateRecordException;

public interface FavoriteListModelInt {
	
	public long add(FavoriteListDto dto) throws ApplicationException, DuplicateRecordException;

	public void delete(FavoriteListDto dto) throws ApplicationException;

	public void update(FavoriteListDto dto) throws ApplicationException, DuplicateRecordException;

	public FavoriteListDto findByPK(long pk) throws ApplicationException;

	public FavoriteListDto findByLogin(String login) throws ApplicationException;

	public List list() throws ApplicationException;

	public List list(int pageNo, int pageSize) throws ApplicationException;

	public List search(FavoriteListDto dto, int pageNo, int pageSize) throws ApplicationException;

	public List search(FavoriteListDto dto) throws ApplicationException;

}



