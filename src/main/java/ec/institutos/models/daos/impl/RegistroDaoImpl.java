package ec.institutos.models.daos.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ec.institutos.models.daos.RegistroDao;
import ec.institutos.models.entities.Funcionario;
import ec.institutos.models.entities.Registro;

@Stateless
public class RegistroDaoImpl extends GenericDaoImpl<Registro, Integer> implements RegistroDao {
	@PersistenceContext
	EntityManager em;

	public RegistroDaoImpl() {

		super(Registro.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Registro> findHoraIngresoSalida(String cedula, Date desde, Date hasta) {
		List<Registro> registros = new ArrayList<Registro>();
		List<Object[]> result = new ArrayList<Object[]>();
		StringBuilder sql = new StringBuilder();
		sql.append(
				"SELECT r.id, r.funcionario, r.fecha, min(r.hora)as hora, max(r.hora)as horaSalida FROM Registro r ");
		if (cedula != null)
			sql.append("WHERE r.funcionario.cedula=:cedula and r.fecha>=:desde and r.fecha<=:hasta ");
		else
			sql.append("WHERE r.fecha>=:desde and r.fecha<=:hasta ");
		sql.append("GROUP BY r.fecha, r.funcionario.id");

		Query query = em.createQuery(sql.toString());
		if (cedula != null)
			query.setParameter("cedula", cedula);

		query.setParameter("desde", desde);
		query.setParameter("hasta", hasta);

		if (!query.getResultList().isEmpty()) {
			result = (List<Object[]>) query.getResultList();
		}
		for (Object[] ob : result) {
			Registro reg = new Registro();
			reg.setId((Integer) ob[0]);
			reg.setFuncionario((Funcionario) ob[1]);
			reg.setFecha((Date) ob[2]);
			reg.setHora((Date) ob[3]);
			reg.setHoraSalida((Date) ob[4]);
			registros.add(reg);
		}
		return registros;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> findHoraIngresoSalidaObject(String cedula, Date fecha) {
		List<Object[]> result = new ArrayList<Object[]>();
		StringBuilder sql = new StringBuilder();
		if (cedula != null) {
			sql.append(getSqlConCedula());
		} else
			sql.append(getSqlSinCedula());

		Query query = em.createNativeQuery(sql.toString());
		if (cedula != null)
			query.setParameter("cedula", cedula);

		query.setParameter("fecha", fecha);

		if (!query.getResultList().isEmpty()) {
			result = (List<Object[]>) query.getResultList();
		}

		return result;
	}

	private String getSqlSinCedula() {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT f.id,f.cedula, f.apellido,f.nombre, t.nombre AS tipoContrato,");
		sql.append("(select r.fecha from registro as r ");
		sql.append("WHERE r.fecha=:fecha and f.id=r.funcionario_id GROUP BY r.fecha, f.id) as fecha,");
		sql.append("(select group_concat(hora order by hora asc  SEPARATOR ',')  from registro as r ");
		sql.append("WHERE r.fecha=:fecha and f.id=r.funcionario_id GROUP BY r.fecha, f.id) as horas ");
		sql.append("FROM funcionario as f ");
		sql.append("inner join tipocontrato as t on t.id=f.tipoContrato_id ");
		sql.append("WHERE f.id IN ");
		sql.append("(SELECT funcionario_id FROM registro as r ");
		sql.append("WHERE r.fecha=:fecha and f.id=r.funcionario_id) ");
		sql.append("UNION ");
		sql.append("SELECT f.id,f.cedula, f.apellido,f.nombre, t.nombre AS tipoContrato,");
		sql.append("IFNULL((select r.fecha from registro as r ");
		sql.append("WHERE r.fecha=:fecha and f.id=r.funcionario_id GROUP BY r.fecha, f.id),:fecha) as fecha,");
		sql.append("(select group_concat(hora order by hora asc  SEPARATOR ',')  from registro as r ");
		sql.append("WHERE r.fecha=:fecha and f.id=r.funcionario_id GROUP BY r.fecha, f.id) as horas ");
		sql.append("FROM funcionario as f ");
		sql.append("inner join tipocontrato as t on t.id=f.tipoContrato_id ");
		sql.append("WHERE f.id not IN ");
		sql.append("(SELECT funcionario_id FROM registro as r ");
		sql.append("WHERE r.fecha=:fecha and f.id=r.funcionario_id)");
		return sql.toString();
	}

	private String getSqlConCedula() {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT f.id,f.cedula, f.apellido,f.nombre, t.nombre AS tipoContrato,");
		sql.append("(select r.fecha from registro as r ");
		sql.append("WHERE r.fecha=:fecha and f.id=r.funcionario_id GROUP BY r.fecha, f.id) as fecha,");
		sql.append("(select group_concat(hora order by hora asc  SEPARATOR ',')  from registro as r ");
		sql.append("WHERE r.fecha=:fecha and f.id=r.funcionario_id GROUP BY r.fecha, f.id) as horas ");
		sql.append("FROM funcionario as f ");
		sql.append("inner join tipocontrato as t on t.id=f.tipoContrato_id ");
		sql.append("WHERE f.id IN ");
		sql.append("(SELECT funcionario_id FROM registro as r ");
		sql.append("WHERE r.fecha=:fecha and f.id=r.funcionario_id) and f.cedula=:cedula ");
		sql.append("UNION ");
		sql.append("SELECT f.id,f.cedula, f.apellido,f.nombre, t.nombre AS tipoContrato,");
		sql.append("IFNULL((select r.fecha from registro as r ");
		sql.append("WHERE r.fecha=:fecha and f.id=r.funcionario_id GROUP BY r.fecha, f.id),:fecha) as fecha,");
		sql.append("(select group_concat(hora order by hora asc  SEPARATOR ',')  from registro as r ");
		sql.append("WHERE r.fecha=:fecha and f.id=r.funcionario_id GROUP BY r.fecha, f.id) as horas ");
		sql.append("FROM funcionario as f ");
		sql.append("inner join tipocontrato as t on t.id=f.tipoContrato_id ");
		sql.append("WHERE f.id not IN ");
		sql.append("(SELECT funcionario_id FROM registro as r ");
		sql.append("WHERE r.fecha=:fecha and f.id=r.funcionario_id)and f.cedula=:cedula");
		return sql.toString();
	}
}
