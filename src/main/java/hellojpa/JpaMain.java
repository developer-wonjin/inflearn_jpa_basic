package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try{

            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);//H2는 시퀀셜전략

            Member1 member = new Member1();
            member.setUsername("member1");
//            member.setTeamId(team.getId());
            member.setTeam(team);
            em.persist(member);

            System.out.println("================================================================");

            em.flush();//쓰기지연버퍼의 SQL을 DB로 flush 하여 Sync맞추기
            em.clear();//영속성컨택스트(1차캐시)모두 비우기
            //flush와 clear를 하는 이유: 아래 SELECT쿼리가 질의되지 않고 1차 캐시에서 가져오는 현상을 막기위해

            Member1 findMember = em.find(Member1.class, member.getId());
            Team findTeam = findMember.getTeam();
            List<Member1> members = findTeam.getMembers();

            for (Member1 member1 : members) {
                System.out.println("m = " + member.getUsername());
            }

            tx.commit();
        }catch (Exception e){
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();
    }
}
