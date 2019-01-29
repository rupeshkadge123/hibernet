import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.otp.hibernate.pojo.Employee;

public class Main {

	public static void main(String[] args) {
		Configuration configuration = new Configuration()
				.configure("hibernate.cfg.xml");
		StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties());
		SessionFactory factory = configuration.buildSessionFactory(builder
				.build());

		Session session = factory.openSession();

		System.out.println("Reading Partial Entity with One Projection object ");

		Criteria criteria = session.createCriteria(Employee.class);
		Projection projection = Projections.property("salary");
		criteria.setProjection(projection);
		List list = criteria.list();
		Iterator it = list.iterator();

		while (it.hasNext()) {
			Integer sal = (Integer) it.next();
			System.out.println("Employee Salary : " + sal);
		}
		
		System.out.println("Reading Partial Entity with multiple Projection objects ");
		
		Criteria crit2 = session.createCriteria(Employee.class);
		Projection projection1 = Projections.property("salary");
		Projection projection2 = Projections.property("departmentId");
		Projection projection3 = Projections.property("employeeName");
		
		ProjectionList pList = Projections.projectionList();
		pList.add(projection1);
		pList.add(projection2);
		pList.add(projection3);
		crit2.setProjection(pList);
		
		List list2 = crit2.list();

		Iterator it2 = list2.iterator();

		while (it2.hasNext()) {
			Object[] obj = (Object[]) it2.next();
			System.out.println("Salary : " + obj[0]+" DeptId : "+obj[1]+" empName : "+obj[2]);
		}
	}
	
	

}
