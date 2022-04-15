package hellojpa.chap03;

import hellojpa.Member1;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaDrityChecking {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try{

            Member1 member1 = em.find(Member1.class, 150L);
            member1.setUsername("AAAAAAAAA");
            //em.persist(member);
            /*
            JPA의 기본사상은 1차캐시를 java컬렉션프레임워크처럼 사용하는 것.
            컬렉션에 객체를 집어넣은 상태에서 객체에 변경을 가했는데 
            객체를 다시 컬렉션에 집어넣나? 아니잖아 따라서 em.persist(member);는 필요없지
             */

            System.out.println("==========================================");

            tx.commit();
        }catch (Exception e){
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();
    }
}
