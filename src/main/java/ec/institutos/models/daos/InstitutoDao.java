package ec.institutos.models.daos;

import javax.ejb.Local;

import ec.institutos.models.entities.Instituto;


@Local
public interface InstitutoDao extends GenericDao<Instituto, Integer> {

}
