/*--------------------------------------------------------------------

Copyright Jonathan Cozzo and Patrick Rannou (22/03/2013)

This software is an Android application whose purpose is to select 
and characterize zones on a photography (type, material, color...).

This software is governed by the CeCILL license under French law and
abiding by the rules of distribution of free software.  You can  use, 
modify and/ or redistribute the software under the terms of the CeCILL
license as circulated by CEA, CNRS and INRIA at the following URL
"http://www.cecill.info". 

As a counterpart to the access to the source code and  rights to copy,
modify and redistribute granted by the license, users are provided only
with a limited warranty  and the software's author,  the holder of the
economic rights,  and the successive licensors  have only  limited
liability. 

In this respect, the user's attention is drawn to the risks associated
with loading,  using,  modifying and/or developing or reproducing the
software by the user in light of its specific status of free software,
that may mean  that it is complicated to manipulate,  and  that  also
therefore means  that it is reserved for developers  and  experienced
professionals having in-depth computer knowledge. Users are therefore
encouraged to load and test the software's suitability as regards their
requirements in conditions enabling the security of their systems and/or 
data to be ensured and,  more generally, to use and operate it in the 
same conditions as regards security. 

The fact that you are presently reading this means that you have had
knowledge of the CeCILL license and that you accept its terms.

-----------------------------------------------------------------------*/

package com.ecn.urbapp.dialogs;

import java.util.Vector;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.net.ParseException;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.ecn.urbapp.db.Element;
import com.ecn.urbapp.db.PixelGeom;
import com.ecn.urbapp.fragments.CharacteristicsFragment;
import com.ecn.urbapp.zones.UtilCharacteristicsZone;
import com.example.osmurbapp.R;

/**
 * This class creates the dialog that indicate which pixelgeoms is not defined
 * 
 * @author Jules Party
 * 
 */
@SuppressLint("NewApi") public class UnionDialogFragment extends DialogFragment {

	/**
	 * The Dialog instance that allows the user to group Elements.
	 */
	private Dialog box;
	/** 
	 * Button to fill the characteristic the selected zones
	 */
	private Button bindStrong = null;
	/**
	 * Button to delete all the characteristics of the selected zones
	 */
	private Button bindWeak = null;
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		box = new Dialog(getActivity());
		box.setContentView(R.layout.layout_definition_dialog_union);
		box.setTitle(R.string.definition_union);
		bindStrong = (Button) box.findViewById(R.id.definition_button_union_strong);
		bindWeak = (Button) box.findViewById(R.id.definition_button_union_weak);
		bindStrong.setOnClickListener(strongUnion);
		bindWeak.setOnClickListener(weakUnion);
		return box;
	}

	private OnClickListener strongUnion = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			Vector<PixelGeom> selectedPixelGeom = UtilCharacteristicsZone.getAllSelectedPixelGeoms();
			try {
				UtilCharacteristicsZone.union(selectedPixelGeom);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			box.dismiss();
			CharacteristicsFragment.getMyImage().invalidate();
		}
	};

	private OnClickListener weakUnion = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			Vector<Element> selectedElements = UtilCharacteristicsZone.getAllSelectedElements();
			for(Element elt : selectedElements){
				elt.setLinkedElement(selectedElements);
			}
			box.dismiss();
			CharacteristicsFragment.getMyImage().invalidate();
		}
	};

	@Override
	public void onCancel(DialogInterface dialog) {
	}
}