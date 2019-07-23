package br.com.ciclic.cervejeira.business.repository;

import br.com.ciclic.cervejeira.business.model.EstiloCerveja;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstiloCervejaRepository extends JpaRepository<EstiloCerveja , Long> {
}
