package hellojpa;

import jakarta.persistence.*;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Member member = em.find(Member.class, 150L);
            member.setName("AAAAA");

            // 영속성 컨텍스트에서 빠진다. -> update 쿼리가 나가지 않고, select 쿼리만 나간다.
            em.detach(member);

            // 엔티티 매니저 안에 있는 영속성 컨텍스트를 통째로 없앤다. -> select 쿼리만 나간다.
            em.clear();

            System.out.println("=====================");

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}