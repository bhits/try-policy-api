/*
 * This code is sample code, provided as-is, and we make NO 
 * warranties as to its correctness or suitability for any purpose.
 * 
 * We hope that it's useful to you. Enjoy. 
 * Copyright LearningPatterns Inc.
 */

package gov.samhsa.tryPolicy.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.text.NumberFormat;

@EnableAspectJAutoProxy
public class ProfilerAspect {

	public Object profile(ProceedingJoinPoint joinPoint) throws Throwable {

		double start = System.nanoTime();
		Object returnValue = joinPoint.proceed();
		elapsed(start, joinPoint);

		return returnValue;
	}

	private void elapsed(double start, ProceedingJoinPoint joinPoint) {
		double elapsed = (System.nanoTime() - start) / secondAsNanos;
		System.out.println(joinPoint.getStaticPart().toShortString()
				+ ": elapsed time in seconds " + fmt.format(elapsed));
	}

	final static long secondAsNanos = 1000000000;
	final static NumberFormat fmt = NumberFormat.getNumberInstance();
	static {
		fmt.setMinimumFractionDigits(10);
	}

}