package in.co.rays.project_3.model;

import java.util.List;

import in.co.rays.project_3.dto.ItemInformationDto;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.exception.DuplicateRecordException;

public interface ItemInformationModelInt {

	public long add(ItemInformationDto dto) throws ApplicationException, DuplicateRecordException;

	public void delete(ItemInformationDto dto) throws ApplicationException;

	public void update(ItemInformationDto dto) throws ApplicationException, DuplicateRecordException;

	public ItemInformationDto findByPK(long pk) throws ApplicationException;

	public ItemInformationDto findByLogin(String login) throws ApplicationException;

	public List list() throws ApplicationException;

	public List list(int pageNo, int pageSize) throws ApplicationException;

	public List search(ItemInformationDto dto, int pageNo, int pageSize) throws ApplicationException;

	public List search(ItemInformationDto dto) throws ApplicationException;

}
