package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaBasic {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();// 정말 쉽게 생각하면 DB 커넥션을 받아왔다고 생각.

        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try{
            Member member = new Member();

            member.setId(2L);
            member.setName("HelloB");

            em.persist(member);

            tx.commit();
        }catch (Exception e){
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();
    }
}
