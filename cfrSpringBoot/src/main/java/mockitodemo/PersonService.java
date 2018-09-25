package mockitodemo;

public class PersonService {
	private final PersonDao personDao;

	public PersonService(PersonDao personDao) {
		this.personDao = personDao;
	}

	public boolean update(int id, String name) {
		Person person = personDao.getPerson(id);
		if (person == null) {
			return false;
		}
		Person personUpdate = new Person(person.getId(), name); // 遗留：没懂，这个person.getId()不就是id吗？直接传id不就好了
		return personDao.update(personUpdate);
	}
}