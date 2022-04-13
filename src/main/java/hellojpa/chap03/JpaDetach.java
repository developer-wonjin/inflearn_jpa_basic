package hellojpa.chap03;

import hellojpa.Member;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaDetach {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try{

            Member member = em.find(Member.class, 1L);
            member.setName("CCCC");

            em.detach(member); //위 1차캐시에서 id=1인 Member객체를 삭제하여 관리대상에서 제외시킴. 따라서 아래 tx.commit()시 update sql이 실행되지 않음.

            Member member2 = em.find(Member.class, 1L);


            tx.commit();
        }catch (Exception e){
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();
    }
}
