//
//  HVLAgendaViewController.swift
//  HuntVision
//
//  Created by Login Informatica on 20/07/15.
//  Copyright (c) 2015 Login Informatica. All rights reserved.
//

import UIKit
import QuartzCore
import TSMessages
class HVLAgendaViewController: UITableViewController {

    var agendas = NSMutableArray()
    
    let dateFormatter = NSDateFormatter()
    
    override func viewDidLoad() {

        super.viewDidLoad()
        
        self.title = "Agenda"
        
        dateFormatter.dateFormat = "dd/MM/yyyy HH:mm"
        
        self.tableView.registerNib(UINib(nibName: "HVLAgendaTableViewCell", bundle: nil), forCellReuseIdentifier: "reuseIdentifier")

        if let results = HVLAgendaStore().getAll() {
            
            var agenda: HVLAgendaEntity!
            
            for var index = 0; index < results.count; ++index {
                
                agenda = results[index] as! HVLAgendaEntity
                
                agenda.clienteOBJ = HVLClienteStore().getById(agenda.cliente)
                
                agendas.addObject(agenda)
                
            }
            
        }
        
        self.tableView.rowHeight = UITableViewAutomaticDimension
        
        self.tableView.reloadData()
        
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
    }
    
    // MARK: TableViewDelegate

    override func numberOfSectionsInTableView(tableView: UITableView) -> Int {
        return 1
    }

    override func tableView(tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return agendas.count
    }

    
    override func tableView(tableView: UITableView, cellForRowAtIndexPath indexPath: NSIndexPath) -> UITableViewCell {
        
        let cell = tableView.dequeueReusableCellWithIdentifier("reuseIdentifier", forIndexPath: indexPath) as! HVLAgendaTableViewCell

        let agenda = agendas.objectAtIndex(indexPath.row) as! HVLAgendaEntity
        
        cell.descricaoLabel.text = "Cliente: \(agenda.clienteOBJ!.nome) \n \(agenda.clienteOBJ!.endereco)"
        
        cell.descricaoLabel.sizeToFit()
        
        cell.dataLabel.text = " Agendado para ".stringByAppendingString(dateFormatter.stringFromDate(agenda.dataHora))
        
        return cell
        
    }
    
    override func tableView(tableView: UITableView, didSelectRowAtIndexPath indexPath: NSIndexPath) {
        
        self.performSegueWithIdentifier("sg_agenda_detalhe", sender: agendas.objectAtIndex(indexPath.row))
        
    }
    
    override func tableView(tableView: UITableView, estimatedHeightForRowAtIndexPath indexPath: NSIndexPath) -> CGFloat {
        
        return 101
        
    }
    
    // MARK: - Navigation

    override func prepareForSegue(segue: UIStoryboardSegue, sender: AnyObject?) {

       let destination = segue.destinationViewController as! HVLAgendaDetalheViewController
        
        destination.agenda = sender as? HVLAgendaEntity
    
    }
 
    
}
