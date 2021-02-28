package efeito_magnetico;

/* Link do desafio: https://dojopuzzles.com/problems/efeito-magnetico/
 * Em programas v�rios programas gr�ficos baseados em vetores, uma ferramenta muito �til para auxiliar no 
 * desenho � o magneto. Resumidamente, uma pequena �rea da tela, ao redor de "pontos importantes" s�o 
 * magn�ticos. Por exemplo, se movermos o cursor do mouse pr�ximo o suficiente de um desses pontos e 
 * come�armos a desenhar, o desenho vai ser iniciado no ponto magn�tico ao inv�s do ponto onde o cursor 
 * se encontra. Por�m, quando o cursor est� distante de um desses pontos, o in�cio do desenho � no pr�prio 
 * ponto.
 * 
 * Alguns exemplos:
 * 
 * Se existe um ponto magn�tico na coordenada (50, 50) e o raio de efeito magn�tico � 5, quando o curso � 
 * movido para a posi��o (49,50), o efeito magn�tico atua e for�a com que o desenho seja feito a partir do 
 * ponto (50,50);
 * 
 * Se existe um ponto magn�tico em (50, 50), o raio de efeito magn�tico � 5 e o cursor est� em (0, 0), n�o 
 * ocorre o efeito magn�tico;
 * 
 * Se existem dois pontos magn�ticos em (50, 50) e (100, 50), quando o mouse est� em (101, 48), o efeito 
 * magn�tico faz com que voc� comece a desenhar em (100, 50);
 * 
 * Se os pontos magn�ticos s�o (50, 50) e (51, 51) e o mouse est� em (51, 52), o desenho se inicia em (51, 51)
 * 
 * Implemente este efeito magn�tico, informando a localiza��o dos pontos magn�ticos, o raio do efeito 
 * magn�tico e o ponto onde o cursor se encontra no momento. COm esses dados, seu programa dever� informar 
 * qual o ponto onde o desenho ir� come�ar realmente.
 * 
 * Authir
 * */

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	static Scanner input = new Scanner(System.in);
	public static void main(String[] args) {
		//Define a quantidade de pontos magn�ticos (x, y) haver�o. 
		int nQuantidadePontos = quantidadePontos(0);
		ArrayList<PontoMagnetico> pontosMagneticos = new ArrayList<PontoMagnetico>();
		
		for(int i = 0; i < nQuantidadePontos; i ++) {
			int xMag, yMag, r;
			System.out.println("Entre o valor de x do " + (i + 1) + "� ponto magn�tico: ");
			xMag = input.nextInt();
			System.out.println("Entre o valor de y do " + (i + 1) + "� ponto magn�tico: ");
			yMag = input.nextInt();
			System.out.println("Entre o valor do raio do " + (i + 1) + "� ponto magn�tico: ");
			r = input.nextInt();
			PontoMagnetico ponto = new PontoMagnetico(xMag, yMag, r);
			pontosMagneticos.add(ponto);
		}
		

		for(int	i =	0; i < pontosMagneticos.size();	i++)	{
			PontoMagnetico p = (PontoMagnetico) pontosMagneticos.get(i);	
			System.out.println("(" + p.getX() + ", " + p.getY() + ") raio: " + p.getRaio());
		}
		
		int xMouse, yMouse;
		System.out.println("Ponto x do mouse: ");
		xMouse = input.nextInt();
		System.out.println("Ponto y do mouse: ");
		yMouse = input.nextInt();
		PontoMouse pontoMouse = new PontoMouse(xMouse, yMouse);
		
		PontoMouse pontoInicio = new PontoMouse();
		pontoInicio = desenhoInicio(pontoMouse, pontosMagneticos);
	
		System.out.println("O mouse iniciar� nos pontos (" + pontoInicio.getX() + ", " + pontoInicio.getY() + ")");
	}
	
	public static int quantidadePontos(int n) {
		do {
			System.out.println("Entre a quantidade de pontos magn�ticos [1] a [5]");
			n = input.nextInt();
			if(n < 1 || n > 5)
				System.out.println("Voc� digitou um n�mero fora dos limites. Digite uma quantidade de 0 a 5");
		}while(n < 1 || n > 5);
		
		return n;
	}
	
	public static PontoMouse desenhoInicio(PontoMouse ponto, ArrayList<PontoMagnetico> list) {
		int distanciaX, distanciaY;
		PontoMouse comparar = new PontoMouse(999999999, 999999999);
		PontoMouse pontoInicio = new PontoMouse();
		boolean entrada = false;
		for(int	i =	0; i < list.size();	i++)	{
			PontoMagnetico p = (PontoMagnetico) list.get(i);	
			distanciaX = Math.abs(p.getX() - ponto.getX());
			distanciaY = Math.abs(p.getY() - ponto.getY());
			if(distanciaX > p.getRaio() || distanciaY > p.getRaio())
				continue;
			else {
				if(comparar.getX() > distanciaX && comparar.getY() > distanciaY) {
					comparar.setX(distanciaX);
					comparar.setY(distanciaY);
					pontoInicio = p;
					entrada = true;
				}
			}
		}
		if(!entrada) 
			return ponto;
		else
			return pontoInicio;
	}
}
