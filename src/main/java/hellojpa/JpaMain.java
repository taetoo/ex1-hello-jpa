package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        // 모든 데이터를 변경하는 것은 트랜젝션 얀에서 이루어져야 한다.
        EntityTransaction tx = em.getTransaction();
        // 트랜젝션 시작하는 메서드
        tx.begin();

        try {
            Member findMemeber = em.find(Member.class, 1L);
            findMemeber.setName("HelloJpa");

//            System.out.println("findMemer.id= " + findMemeber.getId());
//            System.out.println("findMemer.name= " + findMemeber.getName());
            // 변경된 데이터를 DB 테이블에 영구적 반영
            tx.commit();
        } catch (Exception e) {
            // 문제 발생시 롤백
            tx.rollback();
        } finally {
            // EntitiyManager는 내부적으로 DB 커넥션을 통해 동작하기 때문에 항상 닫아줘야한다.
            em.close();
        }
        emf.close();

    }
}
