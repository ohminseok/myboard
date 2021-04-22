package com.myboard.aop;

import java.util.Collections;
import java.util.List;

import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.interceptor.MatchAlwaysTransactionAttributeSource;
import org.springframework.transaction.interceptor.RollbackRuleAttribute;
import org.springframework.transaction.interceptor.RuleBasedTransactionAttribute;
import org.springframework.transaction.interceptor.TransactionInterceptor;

@Configuration
public class TransactionAspect {

	@Autowired //DBConfiguration 클래스에 빈으로 등록한 객체
	private PlatformTransactionManager transactionManager;

	private static final String EXPRESSION = "execution(* com.myboard..service.*Impl.*(..))"; //포인트 컷

	@Bean
	public TransactionInterceptor transactionAdvice() {
		//자바에서 모든 예외는 Exception 클래스에 상속받기 때문에 어떠한 예외가 발생하던 무조건 롤백이 수행
		List<RollbackRuleAttribute> rollbackRules = Collections.singletonList(new RollbackRuleAttribute(Exception.class));

		RuleBasedTransactionAttribute transactionAttribute = new RuleBasedTransactionAttribute();
		transactionAttribute.setRollbackRules(rollbackRules);
		transactionAttribute.setName("*"); //트랜젝션 이름을 설정

		MatchAlwaysTransactionAttributeSource attributeSource = new MatchAlwaysTransactionAttributeSource();
		attributeSource.setTransactionAttribute(transactionAttribute);

		return new TransactionInterceptor(transactionManager, attributeSource);
	}

	@Bean
	public Advisor transactionAdvisor() {

		AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut(); //AOP의 포인트컷을 설정
		pointcut.setExpression(EXPRESSION); //EXPRESSION에 지정한 serviceImpl 클래스의 모든 메서드를 대상으로 설정

		return new DefaultPointcutAdvisor(pointcut, transactionAdvice());
	}

}
