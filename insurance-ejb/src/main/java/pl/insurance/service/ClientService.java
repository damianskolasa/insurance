package pl.insurance.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import pl.insurance.model.Client;


@Stateless
public class ClientService {

	@Inject
	private EntityManager em;

	public Client registerClient(Client client) {
		return em.merge(client);
	}

	public List<Client> listClients() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Client> criteria = cb.createQuery(Client.class);
		Root<Client> client = criteria.from(Client.class);

		criteria.select(client).orderBy(cb.asc(client.get("name")));
		return em.createQuery(criteria).getResultList();
	}

	public Client findClientById(Long clientId) {
		return (Client) em
				.createQuery("Select c from Client c where c.id = :id")
				.setParameter("id", clientId).getSingleResult();
	}

	public Client findClientByPESEL(String pesel) {
		List<Client> clients = em
				.createQuery("Select c from Client c where c.pesel = :pesel",
						Client.class).setParameter("pesel", pesel)
				.getResultList();

		if (clients.isEmpty()) {
			return null;
		} else {
			return clients.get(0);
		}
	}
	
}
