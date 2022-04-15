package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

//양방향 연관관계 주의점
public class JpaCaution {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try{
//            Member1 member = new Member1();
//            member.setUsername("member1");
//            em.persist(member);
//
//            Team team = new Team();
//            team.setName("TeamA");
//            //team.getMembers().add(member); //DB에 아무런 영향(질의)를 하지 않는다. [가짜 맵핑, 조회용맵핑]
//            em.persist(team);

            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

            Member1 member = new Member1();
            member.setUsername("member1");
            member.setTeam(team);//연관관계의 주인인 Member.team필드에 값을 셋팅했을때 INSERT가 질의됨.
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
