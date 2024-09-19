package hellojpa;

import jakarta.persistence.*;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            // 비영속
            Member member = new Member();
            member.setId(1L);
            member.setName("HelloJPA");

            // 영속 상태 (엔티티 매니저 안에 있는 영속성 컨텍스트를 통해서 멤버가 관리된다는 뜻)
            em.persist(member);

            // 커밋찍는 시점에 쿼리가 날라간다.
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}