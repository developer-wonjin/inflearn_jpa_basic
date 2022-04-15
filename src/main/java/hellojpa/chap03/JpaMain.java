package hellojpa.chap03;

import hellojpa.Member1;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();


        try{
            //비영속
            Member1 member1 = new Member1();
            member1.setId(101L);
            member1.setUsername("HelloJPA");

            //영속(실제로는 tx가 끝나야지 영속이 반영됨)
            System.out.println("=== BEFORE ===");
            em.persist(member1);                                //1차 캐시에 101번 Member객체저장
            System.out.println("=== AFTER ===");

            Member1 findMember1 = em.find(Member1.class, 101L);//DB질의 없어도 Member객체를 find할 수 있다.(위 과정에서 1차캐시에 저장해둬서, 1차캐시가 최신데이터임)

            System.out.println("findMember.id = " + findMember1.getId());
            System.out.println("findMember.name = " + findMember1.getUsername());

            tx.commit(); //쿼리가 이때 실행됨
            System.out.println("=== AFTER tx.commit===");
        }catch (Exception e){

            tx.rollback();
        }finally {
            em.close();
        }

        emf.close();
    }
}
