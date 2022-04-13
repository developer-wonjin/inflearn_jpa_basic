package hellojpa.chap03;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaBasic {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");//어플리케이션 로딩시 1개만

        // 정말 쉽게 생각하면 DB 커넥션을 받아왔다고 생각.
        // 고객의 요청마다 생성
        // 쓰레드간에 공유X
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try{
            //추가
//            Member member = new Member();
//            member.setId(2L);
//            member.setName("HelloB");
//
//            Member findMember = em.find(Member.class, 1L);
//
//            //조회
//            System.out.println("findMember.id= " + findMember.getId());
//            System.out.println("findMember.name= " + findMember.getName());

            //조회(JPQL)
//            List<Member> resultList = em.createQuery("select m from Member as m", Member.class)
//                    .setFirstResult(5)
//                    .setMaxResults(8)
//                    .getResultList();//방언을 바꾸더라도 알아서 각 DBMS에 맞는 SQL로 번역해줌
//            for(Member ele : resultList){
//                System.out.println("member.name = " + ele.getName());
//            }

//            //수정
//            findMember.setName("HelloJPA");
//
//            //삭제
//            em.remove(findMember);
//
//            em.persist(findMember);

            tx.commit();
        }catch (Exception e){
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();
    }
}
