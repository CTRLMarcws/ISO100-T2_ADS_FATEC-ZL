package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class RedesController {

	public RedesController()
	{
		super();
	}

	public String verificaOS()
	{
		String os = System.getProperty("os.name");

		return os;
	}

	public void processosAtivos (String oS)
	{
		String processo = "";

		try
		{
			if (oS.contains("Windows"))
			{
				processo = "TASKLIST /FO TABLE";
			}
			else if (oS.contains("Linux"))
			{
				processo = "ps -ef";
			}

			Process p = Runtime.getRuntime().exec(processo);
			InputStream fluxo = p.getInputStream();

			InputStreamReader leitor = new InputStreamReader(fluxo);
			BufferedReader buffer = new BufferedReader(leitor);

			String linha = buffer.readLine();

			while (linha != null)
			{
				System.out.println(linha);
				linha = buffer.readLine();
			}

			buffer.close();
			leitor.close();
			fluxo.close();
		}
		catch (IOException err)
		{
			err.printStackTrace();
		}
	}

	public void matarProcessosOS(String oS, String processo)
	{
		String killPID = "";
		String killNome = "";

		if (oS.contains("Windows"))
		{
			killPID = "TASKKILL /PID";
			killNome = "TASKKILL /IM";
		}
		else if (oS.contains("Linux"))
		{
			killPID = "kill -9";
			killNome = "pkill -f";
		}
		try
		{
			Runtime.getRuntime().exec(matarProcessos(killPID, killNome, processo));
			System.out.println("Processo " + processo + " finalizado com sucesso.");
		}
		catch (IOException err)
		{
			err.printStackTrace();
		}
	}

	public String matarProcessos(String killPID, String killNome, String processo)
	{
		StringBuffer buffer = new StringBuffer();
		int PID = 0;

		try
		{
			PID = Integer.parseInt(processo);
			buffer.append(killPID);
			buffer.append(" ");
			buffer.append(PID);
		}
		catch(NumberFormatException e)
		{
			buffer.append(killNome);
			buffer.append(" ");
			buffer.append(processo);
		}

		return buffer.toString();
	}

}
