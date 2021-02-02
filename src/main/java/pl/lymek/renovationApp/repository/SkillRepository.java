package pl.lymek.renovationApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.lymek.renovationApp.model.Skill;

public interface SkillRepository extends JpaRepository<Skill,Long> {
}
