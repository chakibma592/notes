package com.gestionnote.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class ConfigurationSecurity extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource dataSource;

	@Autowired
	public void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.jdbcAuthentication().dataSource(dataSource)

				.usersByUsernameQuery(
						"Select username as principal, password as credentials, actived from users where username = ?")
				.authoritiesByUsernameQuery(
						"Select u.username as principal, r.libelle as role from users u, role r  where  u.id_role=r.id and u.username = ?")

				.rolePrefix("ROLE_").passwordEncoder(bCryptPasswordEncoder());

//    	  auth
//          .inMemoryAuthentication()
//          .withUser("admin").password("{noop}admin123456").roles("ADMIN").and()
//          .withUser("user").password("{noop}user123456").roles("USER").and()
//    
//          //Gestion du personnel
//          .withUser("ADMINPERSONNEL").password("{noop}adminpersonnel123456").roles("ADMINPERSONNELCOMTRANS","ADMINPERSONNEL1BT","ADMINPERSONNEL2BST","ADMINPERSONNEL4BT","ADMINPERSONNEL8BT").and()
//          .withUser("ADMINPERSONNELCOMTRANS").password("{noop}admin123456").roles("ADMINPERSONNELCOMTRANS").and()
//          .withUser("ADMINPERSONNEL1BT").password("{noop}admin123456").roles("ADMINPERSONNEL1BT").and()
//          .withUser("ADMINPERSONNEL2BST").password("{noop}admin123456").roles("ADMINPERSONNEL2BST").and()
//          .withUser("ADMINPERSONNEL4BT").password("{noop}admin123456").roles("ADMINPERSONNEL4BT").and()
//          .withUser("ADMINPERSONNEL8BT").password("{noop}admin123456").roles("ADMINPERSONNEL8BT").and()
//          
//          
//          
//          .withUser("personnel").password("{noop}personnel").roles("PERSONNEL").and()
//          .withUser("materiel").password("{noop}materiel").roles("MATERIEL").and()
//    	  .withUser("emploi").password("{noop}emploi").roles("EMPLOI").and()
//    	  .withUser("sig").password("{noop}sig").roles("SIG").and()
//    	  .withUser("radar").password("{noop}radar").roles("RADAR");
//          
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		// pour definir les strategies de securités,, les regles
		// on demande a Spring qu'on a besoin de passer par une authentification basée
		// par un formulaire

		http.csrf().disable();

		// securiser les ressources de l'appli
		// http.authorizeRequests().antMatchers("/**").permitAll();

		http.authorizeRequests().antMatchers("/static/**").permitAll();
		http.authorizeRequests().antMatchers("/css/**").permitAll();
		http.authorizeRequests().antMatchers("/dist/**").permitAll();
		http.authorizeRequests().antMatchers("/js/**").permitAll();
		http.authorizeRequests().antMatchers("/assets/**").permitAll();
		http.authorizeRequests().antMatchers("/bower_components/**").permitAll();
		http.authorizeRequests().antMatchers("/fonts/**").permitAll();
		http.authorizeRequests().antMatchers("/faws/**").permitAll();
		http.authorizeRequests().antMatchers("/img/**").permitAll();
		http.authorizeRequests().antMatchers("/plugins/**").permitAll();
		http.authorizeRequests().antMatchers("/template/**").permitAll();
		// http.authorizeRequests().antMatchers("/personnel/getPersonnel").permitAll();

//		http.authorizeRequests().antMatchers("/").permitAll();
//
//		http.authorizeRequests().antMatchers("/home").permitAll();

		// ADMINISTRATEUR
//		
//				http.authorizeRequests()
//				
//				.antMatchers("/user/**","/role/**","/stationradar/**","/batteries/**","/kits/**",
//		"/panne/**","/exps/**","/arrets/**","/ge/**","/typeradar/**","/historiquebies/**","/historiquekits/**",
//		"/historiqueexps/**","/antenneradio/**","/moyenradio/**","/Consulter","/UA/**","/UCM/**",
//		"/CategorieMateriel/**","/Article/**","/UAEE/**","/CategorieMaterielEE/**","/ArticleEE/**",
//		"/PASA/**","/reseau/**","/frequence/**","/stationtrans/**","/Gamme/**","/qualite/**","/secteur/**",
//		"/soussecteur/**","/Unite/**","/PC/**").hasRole("ADMIN");

		// http.authorizeRequests().antMatchers("/**").hasRole("ADMINISTRATEUR@COMTRANS");

		// gestion des utilisateurs
		http.authorizeRequests().

				antMatchers("/user/list", "/role/list", "/user/form", "/role/form").hasRole("ADMINISTRATEUR");

		// gestion des couches SIG
		http.authorizeRequests().

				antMatchers("/NaturePI/list", "/PointInteret/list", "/picture/list")
				.hasRole("ADMINISTRATEUR");



		http.authorizeRequests()

				.anyRequest().authenticated().and().formLogin().defaultSuccessUrl("/home").loginPage("/login")
				.permitAll().and().logout().permitAll();

	}

}
