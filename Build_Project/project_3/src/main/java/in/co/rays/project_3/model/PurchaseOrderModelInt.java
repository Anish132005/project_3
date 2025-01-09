package in.co.rays.project_3.model;

import java.util.List;

import in.co.rays.project_3.dto.PurchaseOrderDTO;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.exception.DuplicateRecordException;

public interface PurchaseOrderModelInt {

	public long add(PurchaseOrderDTO dto) throws ApplicationException, DuplicateRecordException;

	public void delete(PurchaseOrderDTO dto) throws ApplicationException;

	public void update(PurchaseOrderDTO dto) throws ApplicationException, DuplicateRecordException;

	public PurchaseOrderDTO findByPK(long pk) throws ApplicationException;

	public PurchaseOrderDTO findByLogin(String login) throws ApplicationException;

	public List list() throws ApplicationException;

	public List list(int pageNo, int pageSize) throws ApplicationException;

	public List search(PurchaseOrderDTO dto, int pageNo, int pageSize) throws ApplicationException;

	public List search(PurchaseOrderDTO dto) throws ApplicationException;

}
