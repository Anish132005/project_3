package in.co.rays.project_3.model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import in.co.rays.project_3.dto.CustomerDto;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.exception.DuplicateRecordException;
import in.co.rays.project_3.util.HibDataSource;

public class CustomerModelHibImp implements CustomerModelInt {

	@Override
	public long add(CustomerDto dto) throws ApplicationException, DuplicateRecordException {

		CustomerDto existDto = null;

		Session session = HibDataSource.getSession();
		Transaction tx = null;
		try {

			tx = session.beginTransaction();

			session.save(dto);

			dto.getId();
			tx.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			if (tx != null) {
				tx.rollback();

			}
			throw new ApplicationException("Exception in ProductDetails Add " + e.getMessage());
		} finally {
			session.close();
		}

		return dto.getId();
	}

	@Override
	public void delete(CustomerDto dto) throws ApplicationException {

		Session session = null;
		Transaction tx = null;
		try {
			session = HibDataSource.getSession();
			tx = session.beginTransaction();
			session.delete(dto);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			throw new ApplicationException("Exception in ProductDetails Delete" + e.getMessage());
		} finally {
			session.close();
		}

	}

	@Override
	public void update(CustomerDto dto) throws ApplicationException, DuplicateRecordException {

		Session session = null;

		/*
		 * Transaction tx = null; CustomerDto exesistDto = findByLogin(dto.getLogin());
		 * 
		 * if (exesistDto != null && exesistDto.getId() != dto.getId()) { throw new
		 * DuplicateRecordException("Login id already exist"); }
		 * 
		 */ Transaction tx = null;

		try {
			session = HibDataSource.getSession();
			tx = session.beginTransaction();
			session.saveOrUpdate(dto);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			throw new ApplicationException("Exception in ProductDetails update" + e.getMessage());
		} finally {
			session.close();
		}

	}

	@Override
	public CustomerDto findByPK(long pk) throws ApplicationException {

		Session session = null;
		CustomerDto dto = null;
		try {
			session = HibDataSource.getSession();
			dto = (CustomerDto) session.get(CustomerDto.class, pk);

		} catch (HibernateException e) {
			throw new ApplicationException("Exception : Exception in getting Bank by pk");
		} finally {
			session.close();
		}

		return dto;
	}

	@Override
	public CustomerDto findByLogin(String login) throws ApplicationException {

		Session session = null;
		CustomerDto dto = null;
		try {
			session = HibDataSource.getSession();
			Criteria criteria = session.createCriteria(CustomerDto.class);
			criteria.add(Restrictions.eq("login", login));
			List list = criteria.list();
			if (list.size() == 1) {
				dto = (CustomerDto) list.get(0);
			}
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new ApplicationException("Exception in getting ProductDetails by Login " + e.getMessage());

		} finally {
			session.close();
		}

		return dto;
	}

	@Override
	public List list(int pageNo, int pageSize) throws ApplicationException {

		Session session = null;
		List list = null;
		try {
			session = HibDataSource.getSession();
			Criteria criteria = session.createCriteria(CustomerDto.class);
			if (pageSize > 0) {
				pageNo = (pageNo - 1) * pageSize;
				criteria.setFirstResult(pageNo);
				criteria.setMaxResults(pageSize);

			}
			list = criteria.list();

		} catch (HibernateException e) {
			throw new ApplicationException("Exception : Exception in  Banks list");
		} finally {
			session.close();
		}

		return list;
	}

	/*
	 * @Override public List list(int pageNo, int pageSize) throws
	 * ApplicationException { // TODO Auto-generated method stub return null; }
	 */
	@Override
	public List search(CustomerDto dto, int pageNo, int pageSize) throws ApplicationException {

		Session session = null;
		ArrayList<CustomerDto> list = null;
		try {
			session = HibDataSource.getSession();
			System.out.println("---------------------------------");
			Criteria criteria = session.createCriteria(CustomerDto.class);
			if (dto != null) {

				if (dto.getId() != null && dto.getId() > 0) {
					criteria.add(Restrictions.eq("id", dto.getId()));
				}

				if (dto.getClientName() != null && dto.getClientName().length() > 0) {
					criteria.add(Restrictions.like("clientName", dto.getClientName() + "%"));
				}

				if (dto.getLocation() != null && dto.getLocation().length() > 0) {
					criteria.add(Restrictions.like("Location", dto.getLocation() + "%"));
				}

				if (dto.getContactNumber() != null && dto.getContactNumber().length() > 0) {
					criteria.add(Restrictions.like("contactNumber", dto.getContactNumber() + "%"));
				}

				if (dto.getImportance() != null && dto.getImportance().length() > 0) {
					criteria.add(Restrictions.like("importance", dto.getImportance() + "%"));
				}

			}

			if (pageSize > 0) {
				pageNo = (pageNo - 1) * pageSize;
				criteria.setFirstResult(pageNo);
				criteria.setMaxResults(pageSize);
			}
			list = (ArrayList<CustomerDto>) criteria.list();
		} catch (HibernateException e) {
			throw new ApplicationException("Exception in ProductDetails search");
		} finally {
			session.close();
		}

		return list;
	}

	@Override
	public List search(CustomerDto dto) throws ApplicationException {
		// TODO Auto-generated method stub
		return search(dto, 0, 0);
	}

	@Override
	public List list() throws ApplicationException {
		// TODO Auto-generated method stub
		return list(0, 0);
	}

}
