package com.company.mvc4.data;

import com.company.mvc4.dto.CarSearchDto;
import lombok.NonNull;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;

public class CarsRepository {
    private static SessionFactory factory;

    public CarsRepository() {
        try {
            factory = new Configuration().
                    configure().
                            addAnnotatedClass(Owner.class).
                            addAnnotatedClass(Car.class).
                            buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public Iterable<Owner> getOwners() {
        var session = factory.openSession();

        try {
            return session.createQuery("FROM Owner").list();
        } catch (HibernateException exception) {
            System.err.println(exception);
        } finally {
            session.close();
        }

        return new ArrayList<>();
    }

    public Iterable<Car> getCars() {
        var session = factory.openSession();

        try {
            return session.createQuery("FROM Car").list();
        } catch (HibernateException exception) {
            System.err.println(exception);
        } finally {
            session.close();
        }

        return new ArrayList<>();
    }

    public Car getCar(int id) {
        var session = factory.openSession();

        try {
            var car = session.get(Car.class, id);
            return car;
        } catch (HibernateException exception) {
            System.err.println(exception);
        } finally {
            session.close();
        }

        return null;
    }

    public Owner getOwner(int id) {
        var session = factory.openSession();

        try {
            return session.get(Owner.class, id);
        } catch (HibernateException exception) {
            System.err.println(exception);
        } finally {
            session.close();
        }

        return null;
    }

    public Iterable<Car> getCars(CarSearchDto searchDto) {
        var session = factory.openSession();

        try {
            var sql = "FROM Car";

            if(!searchDto.getNumber().isBlank() || !searchDto.getVinNumber().isBlank()) {
                sql += " where ";
            }

            if(!searchDto.getNumber().isBlank()) {
                sql += " number = :search_number";
            }

            if(!searchDto.getVinNumber().isBlank()) {

                if(!searchDto.getNumber().isBlank()) {
                    sql += " and ";
                }

                sql += " vin_number = :search_vin_number";
            }

            var query = session.createQuery(sql);

            if(!searchDto.getNumber().isBlank()) {
                query.setParameter("search_number", searchDto.getNumber());
            }

            if(!searchDto.getVinNumber().isBlank()) {
                query.setParameter("search_vin_number", searchDto.getVinNumber());
            }

            return query.list();

        } catch (HibernateException exception) {
            System.err.println(exception);
        } finally {
            session.close();
        }

        return new ArrayList<>();
    }

    public void save(@NonNull Object item) {
        var session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.update(item);
            tx.commit();
        } catch (HibernateException exception) {
            if(tx != null) {
                tx.rollback();
            }
            System.err.println(exception);
        } finally {
            session.close();
        }
    }

    public void addCar(@NonNull Car car) {
        var session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.save(car);
            tx.commit();
        } catch (HibernateException exception) {
            if(tx != null) {
                tx.rollback();
            }
            System.err.println(exception);
        } finally {
            session.close();
        }
    }

    public void deleteCar(int id) {
        var session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            var car = session.get(Car.class, id);
            if(car != null) {
                session.delete(car);
            }
            tx.commit();
        } catch (HibernateException exception) {
            if(tx != null) {
                tx.rollback();
            }
            System.err.println(exception);
        } finally {
            session.close();
        }
    }
}
