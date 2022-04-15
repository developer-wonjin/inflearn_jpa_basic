package hellojpa.chap03;

import hellojpa.Member1;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaClear {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try{

            Member1 member1 = em.find(Member1.class, 1L);
            member1.setUsername("CCCC");

            em.clear(); //위 1차캐시에서 id=1인 Member객체를 삭제하여 관리대상에서 제외시킴. 따라서 아래 tx.commit()시 update sql이 실행되지 않음.

            Member1 member12 = em.find(Member1.class, 1L);


            tx.commit();
        }catch (Exception e){
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();
    }
}
